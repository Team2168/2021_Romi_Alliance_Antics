// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;

public class IntakeMotor extends SubsystemBase {
  private Servo intakeMotor;
  public static final double servoStartAngle = 0;
  public static final double servoEndAngle = 90;

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
  }

  private static IntakeMotor instance = null;
  public static IntakeMotor getInstance() {
    if (instance == null)
      instance = new IntakeMotor();
    return instance;
  }
  /** Creates a new IntakeMotor. */
  private IntakeMotor() {
    intakeMotor = new Servo(Constants.servoInt);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
