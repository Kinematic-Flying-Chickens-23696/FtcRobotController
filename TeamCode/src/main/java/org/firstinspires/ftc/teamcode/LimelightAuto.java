package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.List;

@Autonomous(name = "LimelightAuto")
public class LimelightAuto extends LinearOpMode {
	@Override
	public void runOpMode() {
		AutonomousMovement autonomousMovement = new AutonomousMovement(hardwareMap, telemetry);
		AutonomousMovement.Movement movement = autonomousMovement.movement();
		AutonomousMovement.Module module = autonomousMovement.module();
		AutonomousMovement.Module.Colorsense colorsense = module.colorsense();
		AutonomousMovement.Module.Limelight limelightHelper = module.limelight();
		Limelight3A limelight = autonomousMovement.limelightDevice();
		limelight.pipelineSwitch(0);
		autonomousMovement.startLimelight();
		
		waitForStart();
		
		if (opModeIsActive()) {
			List<Integer> sequence = limelightHelper.getMonolithOrientation(limelightHelper.getResults());
			if (sequence == List.of(1, 0, 0)) {
				// gpp sequence
				movement.drive(1, 400);
			} else if (sequence == List.of(0, 1, 0)) {
				// pgp sequence
				movement.drive(1, 600);
			} else if (sequence == List.of(0, 0, 1)) {
				// ppg sequence
				movement.drive(1, 800);
			} else {
				// try again to recognize orientation, if still unrecognized, default to ppg
				movement.drive(1, 400);
			}
			for (Integer step : sequence) {
				telemetry.addData("Step", step);
				telemetry.update();
				if (step == 0) {
					AutonomousMovement.DetectedColor detectedColor = colorsense.detectColor(colorsense.getColor());
					if (detectedColor == AutonomousMovement.DetectedColor.PURPLE) {
						module.shooter(1, 3000);
					} else {
						module.cycle();
						AutonomousMovement.DetectedColor colorRecheck = colorsense.detectColor(colorsense.getColor());
						if (colorRecheck == AutonomousMovement.DetectedColor.PURPLE) {
							module.shooter(1, 3000);
						} else {
							module.cycle();
							AutonomousMovement.DetectedColor finalRecheck = colorsense.detectColor(colorsense.getColor());
							if (finalRecheck == AutonomousMovement.DetectedColor.PURPLE) {
								module.shooter(1, 3000);
							} else {
								telemetry.addData("Step:", step);
								telemetry.addData("Looking for color:", "AutonomousMovement.DetectedColor.PURPLE");
								telemetry.addData("Detected color:", detectedColor);
								telemetry.addLine("!!ERROR!! No matching color found! Please recalibrate the color sensor! Proceeding to fire next available ball.");
								module.shooter(1, 3000);
							}
						}
					}
				} else if (step == 1) {
					AutonomousMovement.DetectedColor detectedColor = colorsense.detectColor(colorsense.getColor());
					if (detectedColor == AutonomousMovement.DetectedColor.GREEN) {
						module.shooter(1, 3000);
					} else {
						module.cycle();
						AutonomousMovement.DetectedColor colorRecheck = colorsense.detectColor(colorsense.getColor());
						if (colorRecheck == AutonomousMovement.DetectedColor.GREEN) {
							module.shooter(1, 3000);
						} else {
							module.cycle();
							AutonomousMovement.DetectedColor finalRecheck = colorsense.detectColor(colorsense.getColor());
							if (finalRecheck == AutonomousMovement.DetectedColor.GREEN) {
								module.shooter(1, 3000);
							} else {
								telemetry.addData("Step:", step);
								telemetry.addData("Looking for color:", "AutonomousMovement.DetectedColor.PURPLE");
								telemetry.addData("Detected color:", detectedColor);
								telemetry.addLine("!!ERROR!! No matching color found! Please recalibrate the color sensor! Proceeding to fire next available ball.");
								module.shooter(1, 3000);
							}
						}
					}
				}
			}
		}
	}
}