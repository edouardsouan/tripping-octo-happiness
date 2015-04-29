#include <Wire.h>

#include <Max3421e.h>
#include <Usb.h>
#include <AndroidAccessory.h>

#define  LED       13
#define test       9

AndroidAccessory acc("Google, Inc.",
		     "DemoKit",
		     "DemoKit Arduino Board",
		     "1.0",
		     "http://www.android.com",
		     "0000000012345678");

void setup();
void loop();

void init_leds()
{
	digitalWrite(LED, 1);
	pinMode(LED, OUTPUT);
}

byte b1, b2, b3, b4, c;
void setup()
{
	Serial.begin(115200);
	Serial.print("\r\nStart");

	init_leds();
	c = 0;

	acc.powerOn();
}

void loop()
{
	byte err;
	byte idle;
	static byte count = 0;
	byte msg[3];
	long touchcount;
        long duration, cm;
        const int pingPin = 9;
        
         // The PING))) is triggered by a HIGH pulse of 2 or more microseconds.
          // Give a short LOW pulse beforehand to ensure a clean HIGH pulse:
          pinMode(pingPin, OUTPUT);
          digitalWrite(pingPin, LOW);
          delayMicroseconds(2);
          digitalWrite(pingPin, HIGH);
          delayMicroseconds(5);
          digitalWrite(pingPin, LOW);
        
          // The same pin is used to read the signal from the PING))): a HIGH
          // pulse whose duration is the time (in microseconds) from the sending
          // of the ping to the reception of its echo off of an object.
          pinMode(pingPin, INPUT);
          duration = pulseIn(pingPin, HIGH);
        
          // convert the time into a distance
          cm = microsecondsToCentimeters(duration);
          
	if (acc.isConnected()) {
		int len = acc.read(msg, sizeof(msg), 1);
                msg[0] = 'dist';
                msg[1] = cm >> 8;
	        msg[2] = cm & 0xff;
	        acc.write(msg, 3);
         }
	        delay(10);
}

long microsecondsToCentimeters(long microseconds)
{
  // The speed of sound is 340 m/s or 29 microseconds per centimeter.
  // The ping travels out and back, so to find the distance of the
  // object we take half of the distance travelled.
  return microseconds / 29 / 2;
}
