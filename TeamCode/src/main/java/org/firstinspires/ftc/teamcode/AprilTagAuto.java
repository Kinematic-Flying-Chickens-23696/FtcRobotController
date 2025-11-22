package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp(name = "AprilTagAuto")
public class AprilTagAuto extends OpMode {
	
	private AprilTagProcessor aprilTag;
	private DcMotor fl0;
	private DcMotor fr1;
	private DcMotor bl2;
	private DcMotor br3;
	private Limelight3A ll3a;
	
	@Override
	public void init() {
		
		TagDetection tagDetection = new TagDetection();
		tagDetection.initAprilTag();
		
		fl0 = hardwareMap.get(DcMotor.class, "fl0");
		fr1 = hardwareMap.get(DcMotor.class, "fr1");
		bl2 = hardwareMap.get(DcMotor.class, "bl2");
		br3 = hardwareMap.get(DcMotor.class, "br3");
		ll3a = hardwareMap.get(Limelight3A.class, "limelight");
		
	}
	
	@Override
	public void loop() {
		
		TagDetection tagDetection = new TagDetection();
		
		tagDetection.telemetryAprilTag();
		telemetry.update();
		
	}
	
	class TagDetection {
		private void initAprilTag() {
			aprilTag = AprilTagProcessor.easyCreateWithDefaults();
			VisionPortal visionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "cam0"), aprilTag);
		}
		
		private void telemetryAprilTag() {
			
			List<AprilTagDetection> currentDetections = aprilTag.getDetections();
			telemetry.addData("# AprilTags Detected", currentDetections.size());
			
			for (AprilTagDetection detection : currentDetections) {
				if (detection.id == 21) {
					telemetry.addLine("Detected April Tag with id of 21, sequence GREEN PURPLE PURPLE.");
				} else if (detection.id == 22) {
					telemetry.addLine("Detected April Tag with id of 22, sequence PURPLE GREEN PURPLE.");
				} else if (detection.id == 23) {
					telemetry.addLine("Detected April Tag with id of 23, sequence PURPLE PURPLE GREEN.");
				} else {
					telemetry.addLine("Detected April Tag with id of " + detection.id + ", unrecognized.");
				}
			}
		}
	}
	
	class Movement {
		public void drive() {
			fl0.setPower(-(gamepad1.right_trigger-gamepad1.left_trigger));
			fr1.setPower(-(gamepad1.right_trigger-gamepad1.left_trigger));
			bl2.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
			br3.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
		}
		
		public void slide() {
			fl0.setPower(-gamepad1.right_stick_x);
			fr1.setPower(gamepad1.right_stick_x);
			bl2.setPower(-gamepad1.right_stick_x);
			br3.setPower(gamepad1.right_stick_x);
		}
		
		public void rotation() {
			fl0.setPower(-gamepad1.left_stick_x);
			fr1.setPower(-gamepad1.left_stick_x);
			bl2.setPower(-gamepad1.left_stick_x);
			br3.setPower(-gamepad1.left_stick_x);
		}
	}
}