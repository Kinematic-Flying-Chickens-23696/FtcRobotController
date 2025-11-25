package org.firstinspires.ftc.teamcode.helpers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Helper class for robot movement.
 */
public class MovementHelper {
	private final DcMotor fl0;
	private final DcMotor fr1;
	private final DcMotor bl2;
	private final DcMotor br3;
	private final ElapsedTime runtime;
	private final Telemetry telemetry;
	
	/**
	 * Constructor for MovementHelper.
	 *
	 * @param fl0       Front left motor.
	 * @param fr1       Front right motor.
	 * @param bl2       Back left motor.
	 * @param br3       Back right motor.
	 * @param runtime   ElapsedTime object.
	 * @param telemetry Telemetry object.
	 */
	public MovementHelper(DcMotor fl0, DcMotor fr1, DcMotor bl2, DcMotor br3, ElapsedTime runtime, Telemetry telemetry) {
		this.fl0 = fl0;
		this.fr1 = fr1;
		this.bl2 = bl2;
		this.br3 = br3;
		this.runtime = runtime;
		this.telemetry = telemetry;
	}
	
	/**
	 * Drives the robot forward or backward.
	 *
	 * @param speed  Speed of the motors.
	 * @param timeMs Duration of the movement in milliseconds.
	 */
	public void drive(double speed, long timeMs) {
		setMotorPowers(speed, speed, speed, speed);
		waitFor(timeMs);
	}
	
	/**
	 * Slides the robot left or right.
	 *
	 * @param speed  Speed of the motors.
	 * @param timeMs Duration of the movement in milliseconds.
	 */
	public void slide(double speed, long timeMs) {
		setMotorPowers(speed, -speed, -speed, speed);
		waitFor(timeMs);
	}
	
	/**
	 * Rotates the robot.
	 *
	 * @param speed  Speed of the motors.
	 * @param timeMs Duration of the movement in milliseconds.
	 */
	public void rotation(double speed, long timeMs) {
		setMotorPowers(speed, -speed, speed, -speed);
		waitFor(timeMs);
	}
	
	/**
	 * Stops all motors.
	 */
	public void stop() {
		setMotorPowers(0, 0, 0, 0);
	}
	
	/**
	 * Sets the power for all four drive motors.
	 *
	 * @param flPower Power for the front left motor.
	 * @param frPower Power for the front right motor.
	 * @param blPower Power for the back left motor.
	 * @param brPower Power for the back right motor.
	 */
	private void setMotorPowers(double flPower, double frPower, double blPower, double brPower) {
		fl0.setPower(flPower);
		fr1.setPower(frPower);
		bl2.setPower(blPower);
		br3.setPower(brPower);
	}
	
	/**
	 * Waits for a specified amount of time while displaying telemetry.
	 *
	 * @param timeMs Time to wait in milliseconds.
	 */
	private void waitFor(long timeMs) {
		runtime.reset();
		while (runtime.milliseconds() < timeMs) {
			telemetry.addData("Time Remaining:", "%dms", timeMs - (long) runtime.milliseconds());
			telemetry.update();
		}
		stop();
	}
}

