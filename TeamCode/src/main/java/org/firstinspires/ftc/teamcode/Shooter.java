package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This file contains a minimal example of an iterative (Non-Linear) "OpMode". An OpMode is a
 * 'program' that runs in either the autonomous or the TeleOp period of an FTC match. The names
 * of OpModes appear on the menu of the FTC Driver Station. When an selection is made from the
 * menu, the corresponding OpMode class is instantiated on the Robot Controller and executed.
 *
 * Remove the @Disabled annotation on the next line or two (if present) to add this OpMode to the
 * Driver Station OpMode list, or add a @Disabled annotation to prevent this OpMode from being
 * added to the Driver Station.
 */
@TeleOp

public class Shooter extends OpMode {
	
	public DcMotorEx shootmotor;
	public Servo Servo1;
	//RIGHT HERE IS THE VARIBALES THAT I ADDED AND NEED TO BE ADDDED TO TELEOP ()$&!@)$&!(*%!(@^)#(!@^$*)(!^@$*&)!^@$)
	public double getShot;
	public double velocity;
	
	/* Declare OpMode members. */
	
	
	@Override
	public void init() {
		telemetry.addData("Status", "Initialized");
		shootmotor = hardwareMap.get(DcMotorEx.class, "shootmotor");
		shootmotor.setDirection(DcMotorEx.Direction.REVERSE);
		Servo1 = hardwareMap.get(Servo.class, "Servo1");
		//shooter.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
		
		
	}
	
	/*
	 * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
	 */
	@Override
	public void init_loop() {
	
	}
	
	/*
	 * Code to run ONCE when the driver hits PLAY
	 */
	@Override
	public void start() {
	
	}
	
	/*
	 * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
	 */
	
	public void loop() {
		
		shootmotor.setPower(getShot());
		testingservo();
		velocitycheck();
		telemetry.update();
		
	}
	
	
	private void velocitycheck(){
		telemetry.addData("velocity", velocity);
		//	velocity = shooter.getVelocity();
	}


//these are testing functions that can be commented

	private double getShot(){
		if (gamepad1.a) return 0.3;
		if (gamepad1.b) return 0.5;
		if (gamepad1.y) return 0.7;
		if (gamepad1.x) return 0.9;
		return 0;
	}

	private void testingservo(){
		if (gamepad1.right_bumper) {
			Servo1.setPosition(.75);
		}
		if (gamepad1.left_bumper) {
			Servo1.setPosition(.4);
		}

	}
	
	// end of testing functions
	
	
	
}
