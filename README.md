# 2021_Romi_Frantic_Fetch
Repo to store our code for the Frantic Fetch.

![](images/course.jpg)

Submission videos:
  * Teleop: N/A
  * Auto: N/A

# Documentation

  * RULES PDF LINKY LINKY TODO
  * [Full romi hardware user manual](https://www.pololu.com/docs/0J69/all)
  * WPILib [getting started with Romi documumentation](https://docs.wpilib.org/en/latest/docs/romi-robot/index.html)

  * This project is based on:
    * [this git repo](https://github.com/bb-frc-workshops/romi-examples/tree/main/romi-trajectory-ramsete)
    * See chief delphi post: https://www.chiefdelphi.com/t/trajectory-following-with-the-romi/390505/
   

# Usage:

## Battery installation

> The correct orientation for the batteries is indicated by the battery-shaped holes in the Romi chassis as
> well as the + and - indicators in the chassis itself.

![romi batteries](https://docs.wpilib.org/en/latest/_images/assembly-batteries.png)

## Imaging the pi:

* Download the latest WPILib_PI image from here: https://github.com/wpilibsuite/WPILibPi/releases
* Download and install https://www.balena.io/etcher/
* Connect the MicroSD card to your computer
* Flash the MicroSD card with the image using Etcher by selecting the zip file as the source, 
  your SD card as the destination and click `Flash`.  
  Expect the process to take about 3 minutes on a fairly fast computer.
* When flashing is complete, put the SD card into the pi. Turn on the romi.  
  It will take a while for the pi to finish booting this first time after flashing. Be patient.


## Updating the 32U4 Firmware

If you robot doesn't move at all when you run your code in the simulator through VSCode, you
probably need to perform a firmware update on the 32U4 board (the black PCB on the romi).

It doesn't look like they come with compatible firmware from the factory.

Follow the steps here: https://docs.wpilib.org/en/stable/docs/romi-robot/imaging-romi.html#u4-control-board

If you run into an error when performing the update that says something like `/dev/ttyACM0` not found,
follow the step identified in the gitlab issue here: https://github.com/wpilibsuite/WPILibPi/issues/189#issuecomment-762484883

> * Unplug all USB cables from the Pi, and restart the Pi.  
> * Once it starts up again, verify that the Update Firmware button is NOT enabled
> * Plug in the 32U4 to the Pi, and verify that the Update Firmware button is now enabled


## Setup wifi on the romi:

https://docs.wpilib.org/en/latest/docs/romi-robot/imaging-romi.html

## Updatating the romi web services:
The romi web services application can be updated without re-flashing the SD card image.  
This needs to be done __at least once__ after flashing the pi image image in the previous section.

* Download the latest `romi services .tgz` file from here: https://github.com/wpilibsuite/wpilib-ws-robot-romi/releases
* From the romi web page, navigate to the romi tab
* Click the `Writeable` button at the top of the page:  
  ![](https://docs.wpilib.org/en/stable/_images/romi-enable-writable.png)
* Upload the tgz file in the `Web Service Update` section as shown here: https://docs.wpilib.org/en/stable/docs/romi-robot/web-ui.html#web-service-update  
  ![](https://docs.wpilib.org/en/stable/_images/romi-ui-service-update.png)
* Click `Save`

* When complete you should see the new web services version listed in the `Romi Status` section of the page:    
  ![](https://docs.wpilib.org/en/stable/_images/romi-ui-status.png)


## Deploying code:

https://docs.wpilib.org/en/latest/docs/romi-robot/programming-romi.html#running-a-romi-program

> One aspect where a Romi project differs from a regular FRC robot project is that the code is not deployed
> directly to the Romi. Instead, a Romi project runs on your development computer, and leverages the WPILib
> simulation framework to communicate with the Romi robot.

To run a Romi program:
 * first, ensure that your Romi is powered on.
 * Connect to the WPILibPi-<number> wifi network broadcast by the Romi
 * running the code in simulation mode
 * Put the robot in `Teleoperated` or `Autonomous` modes will cause the code to execute on the romi.

## IMU calibration

The IMU is a sensor on the Romi that can tell us it's rotational position in three dimensions.
For auotonously driving the robot, We are primarily interested in using the IMUs Z axis heading data,
as this can tells us which wat the Romi is facing. For example, we can use it to drive in straight
lines, or turn to face a specific direction.

This sensor's accuracy appears to be fairly sensitive to temperature fluctuations. When it's working correctly, 
and the robot is sitting still, we should see that the robot's heading isn't changing. If the sensor calibration
is off, the sensor will report that the robot is turning, even though it's stationary. We call this error drift.
The drift can be measured and accounted for though by running a calibration routine on the Romi. 

Note that it doesn't appear this sensor can account for temperature fluctuations, so it might be best to have
the robot turned on for a few minutes so it can warm up beore running the calibration. When calibrated you
should observe ~0.01 deg/s drift. An incorrectly calibrated sensor will have ~1.0 deg/sec drift.

https://docs.wpilib.org/en/latest/docs/romi-robot/web-ui.html#imu-calibration  

![imu calibration](https://docs.wpilib.org/en/latest/_images/romi-ui-imu-calibration.png)

