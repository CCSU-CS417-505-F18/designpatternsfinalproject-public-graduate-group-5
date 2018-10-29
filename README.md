
# CS505 - GrovePi Interaction Simplified

Authors: Kim Niehaus, Ga Young Lee, Adrian Ward-Manthey

## What it Does

The interface provided will allow users to interact with the sensors without having to use GrovePi API, allowing the user to work strictly with Java code.

For example, methods would allow users to specify what data they want from the light sensor, such as light intensity, or specify certain messages for the LCD RGB Backlight to display to their users.

## Classes and Interfaces

Device Abstract Class- Provides an abstract class for a device, with common methods and attributes that can then be implemented by various devices a user may need. We have provided LED and LCDScreen as sample implementations.

Sensor Interface- Specifies a method to set the sensor and a method to get data from the sensor. Concrete classes of actual sensors will realize. We have provided LightSensor and TempAndHumiditySensor as sample classes.

ProductFactory Interface- Specifies a method to create a device object and a method to create a sensor object

DeviceAndSensorFactory Class - the class that implements the ProductFactory and will have the functionality to create devices and sensors

## Notes

Make sure to add the jar for jython to the build path in Eclipse
