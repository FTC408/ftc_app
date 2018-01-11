package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robotics on 10/26/2017.
 */
@TeleOp(name="Screw", group="Linear Opmode")
public class screws extends robot{

    DcMotor screwLF, screwLB, screwRF, screwRB;

    @Override
    public void runOpMode() throws InterruptedException
    {
        screwLF = hardwareMap.dcMotor.get("screwLF");
        screwLB = hardwareMap.dcMotor.get("screwLB");
        screwRF = hardwareMap.dcMotor.get("screwRF");
        screwRB = hardwareMap.dcMotor.get("screwRB");

        waitForStart(); //Program is setup by everything above this, wait until play is pressed on the phone

        while (opModeIsActive())
        {
            if(gamepad1.a)
            {
                screwLB.setPower(1);
            }
            else
            {
                screwLB.setPower(-1);
            }
            if(gamepad1.b)
            {
                screwLF.setPower(1);
            }
            else
            {
                screwLF.setPower(-1);
            }
            if(gamepad1.x)
            {
                screwRF.setPower(1);
            }
            else
            {
                screwRF.setPower(-1);
            }
            if(gamepad1.y)
            {
                screwRB.setPower(-1);
            }
            else
            {
                screwRB.setPower(1);
            }






        }
    }


}

