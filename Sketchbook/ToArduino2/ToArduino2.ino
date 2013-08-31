#include<stdlib.h>
#include <Servo.h>

#define A 12
#define B 11
#define E 3

char dir;
int intspd;
int length;
int i;

Servo steer;

void setup(){
  Serial.begin(9600);
  
  pinMode(A, OUTPUT);
  pinMode(B, OUTPUT);
  pinMode(E, OUTPUT);
  
  steer.attach(10);
}


void loop(){
  
  //if there is something to read...
  if(Serial.available()){

    
    //get message and null terminate it
    char command[6];
    char spd[3];
    
    length = Serial.readBytesUntil('|', command, 6);
    command[length] = '\0';


    //Seperate the command (dir) from the option or speed(spd)
    dir = command[0];

    if (length > 1){
      for(i = 0; i+2<length; i++){
        spd[i] = command[i+2];
      }
    Serial.print(dir);
    Serial.print(" ");
    intspd = atol(spd);
    Serial.println(intspd);
    
    }
    else{
    Serial.println(dir);
    }    
    
    
    //handle the processed message
    switch (dir){
      case 'f':
        motor(intspd);
      break;
    
      case 's':
        motor(0);
      break;
    
      case 'b':
        motor(-intspd);
      break;
    
      case 'r':
        steer.write(130);
      break;
      
      case 'c':
        steer.write(90);
      break;
      
      case 'l':
        steer.write(50);
      break;
    }
    
  }
}

//motor control functions
void motor(int speedo){
  if (abs(speedo) > 255){
    halt();
  }
  else if (speedo > 0){
   forward(speedo);
  }
  else if (speedo < 0){
    backward(abs(speedo));
  }
  else if (speedo == 0){
    halt();
  }
}

void forward(int speedo){
  digitalWrite(A, HIGH);
  digitalWrite(B, LOW);
  analogWrite(E, speedo);
}
//backward function
void backward(int speedo){
  digitalWrite(A, LOW);
  digitalWrite(B, HIGH);
  analogWrite(E, speedo);
}
//halt function
void halt(){
  digitalWrite(A, LOW);
  digitalWrite(B, LOW);
  digitalWrite(E, LOW);
}


