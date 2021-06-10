// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// import frc.robot.utils.smartdashboarddatatypes.SmartDashboardDouble;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;


public class Intake extends SubsystemBase {

  public static final double servoStartAngle = 90; //degrees
  public static final double servoEndAngle = 0; //degrees
  private static final double servoUpdateRateSeconds = 0.5; // time to delay between servo commands

  private static double commandedServoAngle = servoStartAngle; // Holds the last commanded servo position (degrees)
  private static double servoHoldoffTime = 0.0; // holds the next time it's ok to actuate the servo after the last recieved command (seconds)
  
  public static Intake instance = null;
  private Servo intakeMotor;

  public double getServoAngle() {
    return intakeMotor.getAngle();
  }

  public void openIntake() {
    // Set the position the servo is moved to
    // Let the periodic task actually move the servo 
    commandedServoAngle = servoEndAngle;
  }

  public void closeIntake() {
    // Set the position the servo is moved to
    // Let the periodic task actually move the servo
    commandedServoAngle = servoStartAngle;
  }

  public static Intake getInstance() {
    if (instance == null)
      instance = new Intake();
    return instance;
  }

  /** Creates a new IntakeMotor. */
  private Intake() {
    intakeMotor = new Servo(Constants.SERVO_PWM_PORT);
    // servoStartAngle = new SmartDashboardDouble("servoStartAngle");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run.
    double currentTime = Timer.getFPGATimestamp();
    double currentPosition = intakeMotor.getAngle();
  
    // Move the servo if: 
    //   1. the current position is different than the last commanded one
    //   2. we've waited at least servoHoldoffTime before moving the servo
    //      (rate limit updating the servo position so we don't stall it out and restart the romi) 
    if ( (currentPosition != commandedServoAngle) && (currentTime > servoHoldoffTime)) {
      intakeMotor.setAngle(commandedServoAngle);
      servoHoldoffTime = Timer.getFPGATimestamp() + servoUpdateRateSeconds;
    }
  }
}
