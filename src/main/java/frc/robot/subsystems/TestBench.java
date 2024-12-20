// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.HardwareConfigs;
import frc.robot.Robot;

public class TestBench extends SubsystemBase {
  public HardwareConfigs hardwareConfigs;

  public SparkMax Neo = new SparkMax(Constants.TestBedConstants.neoID, MotorType.kBrushless);
  public SparkMax Vortex = new SparkMax(Constants.TestBedConstants.vortexID, MotorType.kBrushless);

  /** Creates a new TestBench. */
  public TestBench() {
    this.hardwareConfigs = Robot.hardwareConfigs;


    Neo.configure(hardwareConfigs.vortexConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
    Vortex.configure(hardwareConfigs.vortexConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

  }


  public void setVelocity(double RPM) {

    SparkClosedLoopController controller = Neo.getClosedLoopController();
    double velocitySetpoint = RPM * Constants.TestBedConstants.vortexMaxRPM;


    controller.setReference(velocitySetpoint, ControlType.kVelocity);

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Current velocity", Neo.getEncoder().getVelocity());
    // This method will be called once per scheduler run
  }

}
