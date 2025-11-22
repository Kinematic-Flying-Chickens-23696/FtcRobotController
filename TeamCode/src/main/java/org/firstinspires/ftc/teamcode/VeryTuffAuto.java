package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.AutonomousMovement.Movement;

@Autonomous(name = "VeryTuffAuto", group = "Autonomous")
public class VeryTuffAuto extends LinearOpMode {
	//lightweight maxxing
	Movement movement = new AutonomousMovement.Movement();
	private DcMotor fl0;
	private DcMotor fr1;
	private DcMotor bl2;
	private DcMotor br3;

	@Override
	public void runOpMode() {
		fl0 = hardwareMap.get(DcMotor.class, "fl0");
		fr1 = hardwareMap.get(DcMotor.class, "fr1");
		bl2 = hardwareMap.get(DcMotor.class, "bl2");
		br3 = hardwareMap.get(DcMotor.class, "br3");
		
		waitForStart();
		
		movement.drive(0.5, 1500);
	}
}