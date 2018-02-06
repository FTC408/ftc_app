package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Auto Blue", group = "Auto")
//@Disabled
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
        forward(1, 3000);
        turn(0.5, 30);
        turn(0.5, -30);
        forward(-1, 1500);
        turn(0.6, 180);
        forward(0.5, 1000);
        intakeLeft.setPower(1);
        intakeRight.setPower(-1);
        sleep(1000);
        forward(0.6);
        sleep(1000);
        forward(0);
        sleep(1000);
        forward(-0.4);
        sleep(1500);
        forward(0);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);




    }
}

