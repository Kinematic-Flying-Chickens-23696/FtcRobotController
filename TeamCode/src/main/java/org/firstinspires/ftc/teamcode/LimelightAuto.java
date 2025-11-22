package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.AutonomousMovement.Module;

@Autonomous(name = "LimelightAuto")
public class LimelightAuto extends OpMode {
	private Limelight3A limelight;
	private AutonomousMovement autonomousMovement;
	private Module module;
	private Module.Limelight limelightHelper;
	
	@Override
	public void init() {
		autonomousMovement = new AutonomousMovement(hardwareMap, telemetry);
		module = autonomousMovement.module();
		limelightHelper = module.limelight();
		limelight = autonomousMovement.limelightDevice();
		limelight.pipelineSwitch(0);
	}
	
	@Override
	public void init_loop() {
		autonomousMovement.startLimelight();
	}
	
	@Override
	public void loop() {
		// use helper in loop if needed
	}
	
	@Override
	public void stop() {
		autonomousMovement.stopLimelight();
	}
}

