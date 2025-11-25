package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.helpers.LimelightHelper;
import org.firstinspires.ftc.teamcode.helpers.ModuleHelper;

@Autonomous(name = "LimelightAuto")
public class LimelightAuto extends OpMode {
	private final AutonomousMovement autonomousMovement = new AutonomousMovement(hardwareMap, telemetry);
	ModuleHelper moduleHelper = autonomousMovement.module();
	LimelightHelper limelightHelper = moduleHelper.limelight();
	Limelight3A limelight = hardwareMap.get(Limelight3A.class, "ll3a");
	
	@Override
	public void init() {
		limelight.pipelineSwitch(0);
	}
	
	@Override
	public void init_loop() {
		limelightHelper.start();
	}
	
	@Override
	public void loop() {
		// use helper in loop if needed
	}
	
	@Override
	public void stop() {
		limelightHelper.stop();
	}
}

