package org.firstinspires.ftc.teamcode.helpers;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Helper class for Limelight functionality.
 */
public class LimelightHelper {
	private final Limelight3A limelight;
	private final List<Integer> fiducialIds = new ArrayList<>();
	private boolean isLimelightStarted;
	
	/**
	 * Constructor for LimelightHelper.
	 *
	 * @param limelight Limelight3A device.
	 */
	public LimelightHelper(Limelight3A limelight) {
		this.limelight = limelight;
	}
	
	/**
	 * Starts the Limelight device if not already started.
	 */
	public void start() {
		if (!isLimelightStarted) {
			limelight.start();
			isLimelightStarted = true;
		}
	}
	
	/**
	 * Stops the Limelight device if it is started.
	 */
	public void stop() {
		if (isLimelightStarted) {
			limelight.stop();
			isLimelightStarted = false;
		}
	}
	
	/**
	 * Switches the Limelight pipeline.
	 *
	 * @param pipeline The pipeline to switch to.
	 */
	public void pipelineSwitch(int pipeline) {
		limelight.pipelineSwitch(pipeline);
	}
	
	/**
	 * Gets the detected fiducial IDs.
	 *
	 * @return A list of detected fiducial IDs.
	 */
	public List<Integer> getResults() {
		fiducialIds.clear();
		LLResult result = limelight.getLatestResult();
		if (result != null && result.isValid()) {
			List<LLResultTypes.FiducialResult> detectedTags = result.getFiducialResults();
			for (LLResultTypes.FiducialResult tag : detectedTags) {
				fiducialIds.add(tag.getFiducialId());
			}
		}
		return Collections.unmodifiableList(new ArrayList<>(fiducialIds));
	}
	
	/**
	 * Determines the monolith orientation based on detected tags.
	 *
	 * @param detectedTags A list of detected fiducial IDs.
	 * @return A string representing the monolith orientation.
	 */
	public String getMonolithOrientation(List<Integer> detectedTags) {
		for (Integer tag : detectedTags) {
			switch (tag) {
				case 21:
					return "GREEN PURPLE PURPLE";
				case 22:
					return "PURPLE GREEN PURPLE";
				case 23:
					return "PURPLE PURPLE GREEN";
			}
		}
		return null;
	}
}
