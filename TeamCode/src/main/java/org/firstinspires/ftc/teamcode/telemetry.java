package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Austin on 11/17/2017.
 */

@TeleOp(name = "Telemetry", group = "Test")

public class telemetry extends robot {

    public void runOpMode(){
        init();

        waitForStart();

        while(opModeIsActive())
        {
            if (color.red() > color.blue())
            {
                telemetry.addData("Color Left: ", "Red");
            }
            else if (color.red() < color.blue())
            {
                telemetry.addData("Color Left: ", "Blue");
            }
            else
            {
                telemetry.addData("Color Left: ", "Neither");
            }
            telemetry.update();
        }
    }
}
