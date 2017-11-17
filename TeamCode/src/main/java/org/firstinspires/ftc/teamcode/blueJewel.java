package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Blue Jewel", group = "Auto")
public class blueJewel extends robot
{

    public void runOpMode() throws InterruptedException
    {
        init(0);
        waitForStart();

        jewel(false);
    }
}

