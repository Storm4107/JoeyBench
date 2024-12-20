// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig.ClosedLoopSlot;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.HardwareConfigs;
import frc.robot.Robot;

public class TestBench extends SubsystemBase {
  private HardwareConfigs hardwareConfigs;

  //public SparkMax Neo = new SparkMax(Constants.TestBedConstants.neoID, MotorType.kBrushless);
  public SparkMax Vortex = new SparkMax(Constants.TestBedConstants.vortexID, MotorType.kBrushless);
  public SparkClosedLoopController controller;
  public RelativeEncoder neoEncoder;

  /** Creates a new TestBench. */
  public TestBench() {
    this.hardwareConfigs = Robot.hardwareConfigs;

    controller = Vortex.getClosedLoopController();
    neoEncoder = Vortex.getEncoder();

    Vortex.configure(hardwareConfigs.vortexConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    
  }


  public void setVelocity(double RPM) {

    double velocitySetpoint = RPM * Constants.TestBedConstants.vortexMaxRPM * (1/0.4);
    SmartDashboard.putNumber("Velocity setpoint", velocitySetpoint);
    SmartDashboard.putNumber("Joystick input", RPM);
    SmartDashboard.putNumber("Output", Vortex.getAppliedOutput());

    controller = Vortex.getClosedLoopController();
    controller.setReference(velocitySetpoint, ControlType.kVelocity, 0);
  }

  public void setVoltage(double voltage) {

    double voltageSetpoint = voltage * 12;
    SmartDashboard.putNumber("Joystick input", voltage);
    SmartDashboard.putNumber("Output", Vortex.getAppliedOutput());


    Vortex.setVoltage(voltageSetpoint);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Current velocity", Vortex.getEncoder().getVelocity());
    // This method will be called once per scheduler run
  }

}
