int rawdata = 0; //sensor raw value from analog read

#define USE_ARDUINO_INTERRUPTS true    // Set-up low-level interrupts for most acurate BPM math.
#include <SoftwareSerial.h>
#include <PulseSensorPlayground.h>     // Includes the PulseSensorPlayground Library.   


//  Variables
const int PulseWire = 0;                // PulseSensor PURPLE WIRE connected to ANALOG PIN 0
const int LED13 = 13;                  // The on-board Arduino LED, close to PIN 13.
int Threshold = 550;                  // Determine which Signal to "count as a beat" and which to ignore.                            
PulseSensorPlayground pulseSensor;   // Creates an instance of the PulseSensorPlayground object called "pulseSensor"

int count = 0;                          //count number of pulse that you measure
int realBPM = 0;                      //real BPM rate
long totalBPM = 0;                    //total value of BPM
double realBPMtimesecmin =0.0;       //min value of realBPm 
double realBPMtimesecmax = 0.0; //max value of realBPm 
int timeval = 0;      //time count
int valraw[3];       // value of data
int peak[100];      // value of peak
int test = 1;      //check bluetooth receive status
int testtime = 0; //if 1 then it count time to next pulse 
int timedif; 
int timecount = 0;      //time count 
int timesave[100]; //time save from peak value 
int timesend;      //time send when it peak defined
int timevalfist = 0;               
                 //End for count BPM rate Component
int j = 0;
int maxvalpeak;
                 


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

void loop(){  
  rawdata = analogRead(0); //Read sensor value to variable
  int myBPM = pulseSensor.getBeatsPerMinute();  // Calls function on our pulseSensor object that returns BPM as an "int".
  if((test == 1) & (timeval < 100)){
    timeval++;
   //if (pulseSensor.sawStartOfBeat()) {            // Constantly test to see if "a beat happened". 
      if(myBPM != 0)
        count = count + 1;
      totalBPM = totalBPM + myBPM;
      realBPM = totalBPM / count;
      Serial.println(realBPM);
      serial.println(realBPM);
   //}
  }else if(test == 1 & timeval == 100){
      realBPMtimesecmin = (1500 / realBPM);
      realBPMtimesecmax = (4500 / realBPM);
      Serial.println("sdfgf");
      timeval = 0; //after count bpm set time value into 0;
      test = 0; //set vale to check time difference  
  } 
  if(test == 0){
     timeval++;
     if((timeval > (int)realBPMtimesecmin) & (timeval < (int)realBPMtimesecmax)){
        countTimeForPeak(realBPMtimesecmin,realBPMtimesecmax);
        
     }else if(timeval == (int)realBPMtimesecmax){
        maxvalpeak = peak[0];
        for(int k=0;k < j ;k++){
         if(peak[k]>maxvalpeak){
            maxvalpeak = peak[k];
            timesend = timesave[k];
            
         } 
        }
        j = 0;
        timeval = 0;
        timecount = 0;
        //Serial.println("test");  
        takedifmeasure(timesend); 
        
      }
         
   }
  delay(20);
}
//measure Time difference
void countTimeForPeak(double minval, double maxval){
 
  if(valraw[2] == 0){
      Serial.println("peak");
      for(int i =0;i<3;i++){
        valraw[i] = rawdata;
      }
      timecount++;
      if((valraw[1]>valraw[2]) & (valraw[0]<valraw[1])){
        j++;
        peak[j] = valraw[2];
        
        
      }
   }else{
      timecount++;
      memcpy(valraw, &valraw[1], sizeof(valraw) - sizeof(int));
      valraw[2] =  rawdata;
      if((valraw[1]==valraw[2])){
        j++;
        peak[j] = valraw[2];
        timesave[j] = timecount;
      }
  
   }
   }
void takedifmeasure(long tal1){
  
  if(timevalfist == 0){
    timevalfist = tal1;
    
  }else{
    
     timedif = tal1 + (int)(60 / realBPM);
     
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
      Serial.println("1");
    }else if(com == 2 & i == 2){
      p++;
      Serial.println("1");
    }else{
       n++;
      Serial.println("0");
    }
    com = i;
  }  
}
