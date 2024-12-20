// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.security.cert.CRLReason;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.ClosedLoopSlot;

/** Add your docs here. */
public final class HardwareConfigs {

    // init

    public static SparkMaxConfig vortexConfig = new SparkMaxConfig();
    public static SparkMaxConfig neoConfig = new SparkMaxConfig();

    public HardwareConfigs() {

        vortexConfig.inverted(Constants.TestBedConstants.vortexInvert);
        vortexConfig.idleMode(Constants.TestBedConstants.vortexIdleMode);
        vortexConfig.smartCurrentLimit(Constants.TestBedConstants.vortexCurrentLimit);
        vortexConfig.encoder.positionConversionFactor(Constants.TestBedConstants.vortexGearRatio);
        vortexConfig.openLoopRampRate(Constants.TestBedConstants.openLoopRamp);
        vortexConfig.closedLoop.p(Constants.TestBedConstants.vortexkP, ClosedLoopSlot.kSlot0);
        vortexConfig.closedLoop.d(Constants.TestBedConstants.vortexkD, ClosedLoopSlot.kSlot0);

    }

}
