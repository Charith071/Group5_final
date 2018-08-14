int rawdata = 0; //sensor raw value from analog read

#define USE_ARDUINO_INTERRUPTS true    // Set-up low-level interrupts for most acurate BPM math.
#include <SoftwareSerial.h>
#include <PulseSensorPlayground.h>     // Includes the PulseSensorPlayground Library.   


//  Variables
const int PulseWire = 0;                // PulseSensor PURPLE WIRE connected to ANALOG PIN 0
const int LED13 = 13;                  // The on-board Arduino LED, close to PIN 13.
int Threshold = 550;                  // Determine which Signal to "count as a beat" and which to ignore.                            
PulseSensorPlayground pulseSensor;   // Creates an instance of the PulseSensorPlayground object called "pulseSensor"

long count = 0;                          //count number of pulse that you measure
long realBPM = 0;                      //real BPM rate
long totalBPM = 0;                    //total value of BPM
double realBPMtimesecmin =0.0;       //min value of realBPm 
double realBPMtimesecmax = 0.0; //max value of realBPm 
long timeval = 0;      //time count
long valraw[3];       // value of data
long peak[100];      // value of peak
long test = 1;      //check bluetooth receive status
long testtime = 0; //if 1 then it count time to next pulse 
long timedif;  
long timevalfist = 0;               
                 //End for count BPM rate Component
                 


volatile int BPM;                   // used to hold the pulse rate
volatile int Signal;                // holds the incoming raw data
volatile int IBI = 600;             // holds the time between beats, must be seeded! 
volatile boolean Pulse = false;     // true when pulse wave is high, false when it's low
volatile boolean QS = false;        // becomes true when Arduoino finds a beat.

//dilkasun071
int v = 0;                    //v for T(n)
int c;                        
int com = 0;                  //com for v(n)
int pre;                      //pre for count hrv value
int p =0,n = 0;              //p for pbfs , n for nbfs
//  VARIABLES
int blinkPin = 13;                // pin to blink led at each beat

//Send Value to Mobile Phone
const int RX_PIN = 3;
const int TX_PIN = 2;
SoftwareSerial serial(RX_PIN, TX_PIN);


void setup() {   

  Serial.begin(9600);          // For Serial Monitor
  serial.begin(38400);
  
  rawdata = analogRead(0); //Read sensor value to variable

  char checkres[20]="";
  readSerial(checkres);
  if(checkres == "test"){
    if(test == 1){
      // Configure the PulseSensor object, by assigning our variables to it. 
      pulseSensor.analogInput(PulseWire);   
      pulseSensor.blinkOnPulse(LED13);       //auto-magically blink Arduino's LED with heartbeat.
      pulseSensor.setThreshold(Threshold);   
      // Double-check the "pulseSensor" object was created and "began" seeing a signal. 
      if (pulseSensor.begin()) {
        Serial.println("We created a pulseSensor Object !");  //This prints one time at Arduino power-up,  or on Arduino reset.  
      }  
    }      
  }
}
void readSerial(char *inData) {
 
  if (Serial.available() > 0) {
    int h = Serial.available();
    for (int i = 0; i < h; i++) {
      inData[i]=(char)Serial.read();
      inData[i+1]='\0';
    }
  }
 
}
void loop(){
  int myBPM = pulseSensor.getBeatsPerMinute();  // Calls function on our pulseSensor object that returns BPM as an "int".
                                               // "myBPM" hold this BPM value now. 

   
  if((test == 1) & (timeval < 30)){
    timeval++;
    if (pulseSensor.sawStartOfBeat()) {            // Constantly test to see if "a beat happened". 
      count = count + 1;
      totalBPM = totalBPM + myBPM;
      realBPM = totalBPM / count;
      Serial.println(realBPM);
      serial.println(realBPM);
     }
    delay(20);                    // considered best practice in a simple sketch.
    realBPMtimesecmin = (60 / realBPM)*0.5;
    realBPMtimesecmax = (60 / realBPM)*1.5;
      timeval = 0; //after count bpm set time value into 0;
      test = 0; //set vale to check time difference
  }   
  //if test value = 0 then count time from one peak to other
  if(test == 0){
    countTimeForPeak(realBPMtimesecmin,realBPMtimesecmax);
    timeval = timeval + 1;
  }
  
}
//measure Time difference
void countTimeForPeak(double minval, double maxval){
  int j = 0;
  int maxvalpeak;
  if(timeval == 0){
    if(valraw[2] = 0){
      for(int i =0;i<3;i++){
        valraw[i] = rawdata;
      }
      if((valraw[2]>valraw[0]) & (valraw[2]<valraw[1])){
        j++;
        peak[j] = valraw[2];
      }
      maxvalpeak = peak[0];
      for(int k=0;k < j ;k++){
       if(peak[k]>maxvalpeak){
          maxvalpeak = peak[k];
       } 
      }
    }
    timeval = 1;  
  }else{
      memcpy(valraw, &valraw[1], sizeof(valraw) - sizeof(int));
      valraw[2] =  rawdata;
      if((valraw[2]>valraw[0]) & (valraw[2]<valraw[1])){
        j++;
        peak[j] = valraw[2];
      }
      maxvalpeak = peak[0];
      for(int k=0;k < j ;k++){
       if(peak[k]>maxvalpeak){
          maxvalpeak = peak[k];
       } 
      }
      takedifmeasure(timeval);
  }
  delay(20);
}
void takedifmeasure(long tal1){
  if(timevalfist == 0){
    timevalfist = tal1;
  }else{
     timedif = tal1 + (60 / realBPM);
     hrv_algo(timedif); 
  }
}
//measure hrv
void hrv_algo(int interval_time){
  if(v == 0){
    v = interval_time;
  }else{
    if(v < interval_time){
      checkVvalue(1);
    }else if(v > interval_time){
      checkVvalue(2);
    }else if(v == interval_time){
      checkVvalue(3);
    }
    v = interval_time;
  }
}

void checkVvalue(int type){
  if(type == 1){
    compareVal(1);
  }else if(type == 2){
    compareVal(2);
  }else if(type == 3){
    compareVal(3);
  }else{
      Serial.println(4);
  }
}

void compareVal(int i){
  if(com == 0){
    com = i;
  }else if(com != -1){
    if(com == 1 & i == 1){
      p++;
    }else if(com == 2 & i == 2){
      p++;
    }else{
       n++;
    }
    com = i;
  }  
}
