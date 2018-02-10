package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Austin on 10/25/2017.
 */
@Autonomous(name= "Blue Jewel FAST", group = "Auto")
@Disabled
public class blueJewelFAST extends robotFAST
{

    public void runOpMode() throws InterruptedException
    {
        init(0);
        //Sets the jewel arm position to the best spot
        jewelSwivel.setPosition(straightPosition);
        jewel.setPosition(upPosition);
        waitForStart();

        jewel(false);


    }
}

