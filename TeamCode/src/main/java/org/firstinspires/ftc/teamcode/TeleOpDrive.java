package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOpDrive")

public class TeleOpDrive extends OpMode {
	
	public DcMotor fl0;
	public DcMotor fr1;
	public DcMotor bl2;
	public DcMotor br3;
	public DcMotor shootmotor;
	public Servo servo0;
	
	Movement movement = new Movement();
	Module module = new Module();
	Module.Shooter shooter = module.new Shooter();
	
	@Override
	public void init() {
		
		// fl0 must be negative for forward
		// fr1 must be negative for forward
		// bl2 must be positive for forward
		// br3 must be positive for forward
		fl0 = hardwareMap.get(DcMotor.class, "fl0");
		fr1 = hardwareMap.get(DcMotor.class, "fr1");
		bl2 = hardwareMap.get(DcMotor.class, "bl2");
		br3 = hardwareMap.get(DcMotor.class, "br3");
		shootmotor = hardwareMap.get(DcMotor.class, "shootmotor");
		servo0 = hardwareMap.get(Servo.class, "servo0");
		
		fl0.setDirection(DcMotor.Direction.REVERSE);
		fr1.setDirection(DcMotor.Direction.FORWARD);
		bl2.setDirection(DcMotor.Direction.REVERSE);
		br3.setDirection(DcMotor.Direction.FORWARD);
		shootmotor.setDirection(DcMotor.Direction.FORWARD);
		
	}
	
	@Override
	public void loop() {
		
		movement.drive();
		movement.slide();
		movement.rotation();
		shooter.motor();
		shooter.servo();
		
	}
	
	private class Movement {
		
		public void drive() {
			
			fl0.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
			fr1.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
			bl2.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
			br3.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
			
		}
		
		public void slide() {
			
			fl0.setPower(gamepad1.right_stick_x);
			fr1.setPower(-gamepad1.right_stick_x);
			bl2.setPower(-gamepad1.right_stick_x);
			br3.setPower(gamepad1.right_stick_x);
			
		}
		
		public void rotation() {
			
			fl0.setPower(gamepad1.left_stick_x);
			fr1.setPower(-gamepad1.left_stick_x);
			bl2.setPower(gamepad1.left_stick_x);
			br3.setPower(-gamepad1.left_stick_x);
			
		}
	}
	
	private class Module {
		public class Shooter {
			public void motor() {
				if (gamepad2.a) {
					shootmotor.setPower(.3);
				} else if (gamepad2.b) {
					shootmotor.setPower(.5);
				} else if (gamepad2.y) {
					shootmotor.setPower(.7);
				} else if (gamepad2.x) {
					shootmotor.setPower(.9);
				} else {
					shootmotor.setPower(0);
				}
			}
			
			public void servo() {
				if (gamepad2.right_bumper) {
					servo0.setPosition(.75);
				}
				if (gamepad2.left_bumper) {
					servo0.setPosition(.4);
				}
			}
		}
	}
}