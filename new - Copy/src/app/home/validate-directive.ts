import { Validator,NG_VALIDATORS,AbstractControl } from '@angular/forms';
import { Directive,Input } from '@angular/core';

@Directive({
  selector:'[appConfirmvalidator]',
  providers:[{
    provide:NG_VALIDATORS,
    useExisting:confirmValidator,
    multi:true
  }]
})
export class confirmValidator implements Validator{
  @Input() appConfirmvalidator:string;
  //we use this input the field which should be compared with the confirm field

  validate(control:AbstractControl): {[key:string]: any } | null{
    const controlToCompare = control.parent.get(this.appConfirmvalidator);

    if(controlToCompare && controlToCompare.value !== control.value){
      return { 'notEqual':true };
    }
    else{
      return null;
    }

  }

}
