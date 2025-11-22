package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Collections;
import java.util.List;

import org.firstinspires.ftc.teamcode.AutonomousMovement.Movement;
import org.firstinspires.ftc.teamcode.AutonomousMovement.Module;

@Autonomous(name = "LimelightAuto")
public abstract class LimelightAuto extends OpMode {
	
 	private Limelight3A ll3a;
	private DcMotor fl0;
	private DcMotor fr1;
	private DcMotor bl2;
	private DcMotor br3;
	private DcMotor shoot0;
	
	private final ElapsedTime runtime = new ElapsedTime();
	
	private boolean isLimelightStarted = false;
	List<Object> FiducialIds = Collections.emptyList();
	Module module = new Module();
	Movement movement = new AutonomousMovement.Movement();
	Module.Limelight limelight = new Module.Limelight();
	
	@Override
	public void init() {
		fl0 = hardwareMap.get(DcMotor.class, "fl0");
		fr1 = hardwareMap.get(DcMotor.class, "fr1");
		bl2 = hardwareMap.get(DcMotor.class, "bl2");
		br3 = hardwareMap.get(DcMotor.class, "br3");
		shoot0 = hardwareMap.get(DcMotor.class, "shoot0");
		ll3a = hardwareMap.get(Limelight3A.class, "limelight");
		
		ll3a.pipelineSwitch(0);
	}
	
	@Override
	public void init_loop() {
		if (!isLimelightStarted) {
			ll3a.start();
			isLimelightStarted = true;
		}
	}
	
	@Override
	public void start() {
        // movement to get limelight in view of apriltags should go here
        while (limelight.getMonolithOrientation(limelight.getResults()) == null) {
			telemetry.addLine("No valid result from Limelight");
			telemetry.update();
        }
		String orientation = limelight.getMonolithOrientation(limelight.getResults());
		telemetry.addLine("Orientation found! Value: " + orientation);
		telemetry.update();
	}
}