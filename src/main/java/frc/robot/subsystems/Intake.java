// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// import frc.robot.utils.smartdashboarddatatypes.SmartDashboardDouble;
import edu.wpi.first.wpilibj.Servo;

public class Intake extends SubsystemBase {

  // public SmartDashboardDouble servoStartAngle;
  public static final double servoStartAngle = 90;
  public static final double servoEndAngle = 0;
  
  public static Intake instance = null;
  private Servo intakeMotor;

  public void setServoAngle(double servoAngle) {
    intakeMotor.setAngle(servoAngle);
  }

  public double getServoAngle() {
    return intakeMotor.getAngle();
  }

  public void openIntake() {
    setServoAngle(servoEndAngle);
  }

  public void closeIntake() {
    setServoAngle(servoStartAngle);
    // setServoAngle(servoStartAngle.get());
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
    // This method will be called once per scheduler run
  }
}
