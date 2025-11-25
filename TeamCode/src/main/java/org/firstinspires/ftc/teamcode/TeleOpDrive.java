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
		// fr1 must be positive for forward
		// bl2 must be negative for forward
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
		module.logPowerToTelemetry();
		
	}
	
	/**
	 * Movement class handles any gamepad input from gamepad1 relating to robot movement and supplies power to drive motors accordingly.
	 */
	private class Movement {
		
		/**
		 * Reads gamepad1.right_trigger and subtracts `gamepad1.left_trigger` to safely supply power to drive motors for Y-axis movement.
		 */
		public void drive() {
			
			fl0.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
			fr1.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
			bl2.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
			br3.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
			
		}
		
		/**
		 * Reads gamepad1.right_stick_x to safely supply power to drive motors for X-axis movement.
		 */
		public void slide() {
			
			fl0.setPower(gamepad1.right_stick_x);
			fr1.setPower(-gamepad1.right_stick_x);
			bl2.setPower(-gamepad1.right_stick_x);
			br3.setPower(gamepad1.right_stick_x);
			
		}
		
		/**
		 * Reads gamepad1.left_stick_x to safely supply power to drive motors for rotation movement.
		 */
		public void rotation() {
			
			fl0.setPower(gamepad1.left_stick_x);
			fr1.setPower(-gamepad1.left_stick_x);
			bl2.setPower(gamepad1.left_stick_x);
			br3.setPower(-gamepad1.left_stick_x);
			
		}
	}
	
	/**
	 * Module class handles any gamepad2 input to control any module (except movement functions) on the robot.
	 */
	private class Module {
		/**
		 * Shooter class consists of methods to control the shooter motor and servo based on gamepad2 input.
		 */
		public class Shooter {
			/**
			 * Sets shooter motor power based on gamepad2 button inputs.
			 * A = 0.3 power
			 * B = 0.5 power
			 * Y = 0.7 power
			 * X = 0.9 power
			 * No button = 0 power
			 */
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
			
			/**
			 * Sets servo0 position based on gamepad2 bumper inputs.
			 * Right bumper = 0.75
			 * Left bumper = 0.4
			 */
			public void servo() {
				if (gamepad2.right_bumper) {
					servo0.setPosition(.75);
				}
				if (gamepad2.left_bumper) {
					servo0.setPosition(.4);
				}
			}
		}
		
		/**
		 * Logs the current power of each drive motor and position of all servos for debugging purposes.
		 */
		public void logPowerToTelemetry() {
			telemetry.addData("fl0 Power", fl0.getPower());
			telemetry.addData("fr1 Power", fr1.getPower());
			telemetry.addData("bl2 Power", bl2.getPower());
			telemetry.addData("br3 Power", br3.getPower());
			telemetry.addData("shootmotor Power", shootmotor.getPower());
			telemetry.addData("servo0 Position", servo0.getPosition());
		}
	}
}