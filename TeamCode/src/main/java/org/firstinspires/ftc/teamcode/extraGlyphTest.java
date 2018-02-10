package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Glyphs", group = "Auto")
@Disabled
public class extraGlyphTest extends robot
{



    public void runOpMode() throws InterruptedException
    {
        init(0);

        //Sets the jewel arm position to the best spot
        jewelSwivel.setPosition(straightPosition);
        jewel.setPosition(upPosition);
        waitForStart();

        intakeLeft.setPower(-1);
        intakeRight.setPower(1);
        forward(1, 1200);
        sleep(500);
        turn(1, 30);
        sleep(500);
        turn(1, -30);
        sleep(500);
        forward(-1, 500);
        sleep(500);
        turn(1, 120);
        sleep(200);
        forward(1, 500);
        intakeLeft.setPower(1);
        intakeRight.setPower(-1);
        sleep(500);
        forward(1);
        sleep(500);
        forward(0);
        sleep(200);
        forward(-1);
        sleep(500);
        forward(0);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);




    }
}

