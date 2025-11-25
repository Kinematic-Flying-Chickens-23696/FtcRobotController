package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.helpers.LimelightHelper;
import org.firstinspires.ftc.teamcode.helpers.ModuleHelper;
import org.firstinspires.ftc.teamcode.helpers.MovementHelper;

/**
 * AutonomousMovement class handles all robot functions during the autonomous period.
 * Intended to be imported into a LinearOpMode class for use.
 */
public class AutonomousMovement {
	private final Telemetry telemetry;
	private final MovementHelper movementHelper;
	private final ModuleHelper moduleHelper;
	private final ElapsedTime runtime = new ElapsedTime();
	
	/**
	 * Constructor for AutonomousMovement class.
	 *
	 * @param hardwareMap HardwareMap object from the OpMode.
	 * @param telemetry   Telemetry object from the OpMode.
	 */
	public AutonomousMovement(HardwareMap hardwareMap, Telemetry telemetry) {
		this.telemetry = telemetry;
		Limelight3A limelight = hardwareMap.get(Limelight3A.class, "ll3a");
		DcMotor fl0 = hardwareMap.get(DcMotor.class, "fl0");
		DcMotor fr1 = hardwareMap.get(DcMotor.class, "fr1");
		DcMotor bl2 = hardwareMap.get(DcMotor.class, "bl2");
		DcMotor br3 = hardwareMap.get(DcMotor.class, "br3");
		DcMotor shootmotor = hardwareMap.get(DcMotor.class, "shootmotor");
		
		this.movementHelper = new MovementHelper(fl0, fr1, bl2, br3, runtime, telemetry);
		LimelightHelper limelightHelper = new LimelightHelper(limelight);
		this.moduleHelper = new ModuleHelper(shootmotor, runtime, telemetry, limelightHelper);
	}
	
	/**
	 * Returns the MovementHelper object.
	 *
	 * @return MovementHelper object.
	 */
	public MovementHelper movement() {
		return movementHelper;
	}
	
	/**
	 * Returns the ModuleHelper object.
	 *
	 * @return ModuleHelper object.
	 */
	public ModuleHelper module() {
		return moduleHelper;
	}
	
	/**
	 * Displays the remaining runtime on telemetry.
	 *
	 * @param timeMs Total time in milliseconds.
	 * @return The remaining time in milliseconds.
	 */
	public double runtimeRemaining(long timeMs) {
		return timeMs - runtime.milliseconds();
	}
}