package org.firstinspires.ftc.teamcode.helpers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Helper class for robot modules.
 */
public class ModuleHelper {
	private final DcMotor shootmotor;
	private final ElapsedTime runtime;
	private final Telemetry telemetry;
	private final LimelightHelper limelightHelper;
	
	/**
	 * Constructor for ModuleHelper.
	 *
	 * @param shootmotor      Shooter motor.
	 * @param runtime         ElapsedTime object.
	 * @param telemetry       Telemetry object.
	 * @param limelightHelper LimelightHelper object.
	 */
	public ModuleHelper(DcMotor shootmotor, ElapsedTime runtime, Telemetry telemetry, LimelightHelper limelightHelper) {
		this.shootmotor = shootmotor;
		this.runtime = runtime;
		this.telemetry = telemetry;
		this.limelightHelper = limelightHelper;
	}
	
	/**
	 * Runs the shooter motor for a specified duration.
	 *
	 * @param speed  Speed of the shooter motor.
	 * @param timeMs Duration of the action in milliseconds.
	 */
	public void shooter(double speed, long timeMs) {
		shootmotor.setPower(speed);
		runtime.reset();
		while (runtime.milliseconds() < timeMs) {
			telemetry.addData("Time Remaining:", "%dms", timeMs - (long) runtime.milliseconds());
			telemetry.update();
		}
		shootmotor.setPower(0);
	}
	
	/**
	 * Returns the LimelightHelper object.
	 *
	 * @return LimelightHelper object.
	 */
	public LimelightHelper limelight() {
		return limelightHelper;
	}
}

