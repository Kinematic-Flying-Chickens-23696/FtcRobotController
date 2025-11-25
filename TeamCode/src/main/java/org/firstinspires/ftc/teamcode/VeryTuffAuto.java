package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.helpers.MovementHelper;

@Autonomous(name = "VeryTuffAuto", group = "Autonomous")
public class VeryTuffAuto extends LinearOpMode {
	//lightweight maxxing
	@Override
	public void runOpMode() {
		AutonomousMovement autonomousMovement = new AutonomousMovement(hardwareMap, telemetry);
		MovementHelper movement = autonomousMovement.movement();
		
		waitForStart();
		
		movement.drive(0.5, 1125);
	}
}