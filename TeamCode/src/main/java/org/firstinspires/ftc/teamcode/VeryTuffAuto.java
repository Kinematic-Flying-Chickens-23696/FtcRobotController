package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.AutonomousMovement.Movement;

@Autonomous(name = "VeryTuffAuto", group = "Autonomous")
public class VeryTuffAuto extends LinearOpMode {
	@Override
	public void runOpMode() {
		DcMotor fl0 = hardwareMap.get(DcMotor.class, "fl0");
		DcMotor fr1 = hardwareMap.get(DcMotor.class, "fr1");
		DcMotor bl2 = hardwareMap.get(DcMotor.class, "bl2");
		DcMotor br3 = hardwareMap.get(DcMotor.class, "br3");
		
		AutonomousMovement autonomousMovement = new AutonomousMovement(hardwareMap, telemetry);
		Movement movement = autonomousMovement.movement();
		
		waitForStart();
		
		movement.drive(0.5, 1500);
	}
}