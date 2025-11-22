package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp(name = "April Tag Testing")
public class AprilTagTestingAgain extends LinearOpMode {
    private AprilTagProcessor aprilTag;
    
    @Override
    public void runOpMode() {
        initAprilTag();
        waitForStart();
        
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                telemetryAprilTag();
                telemetry.update();
            }
        }
    }
    
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
