// Problem Link: https://leetcode.com/problems/angle-between-hands-of-a-clock?envType=daily-question&envId=2026-06-18

/*

Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

Answers within 10-5 of the actual value will be accepted as correct.

Example 1:
Input: hour = 12, minutes = 30
Output: 165

Example 2:
Input: hour = 3, minutes = 30
Output: 75

Example 3:
Input: hour = 3, minutes = 15
Output: 7.5


Approach: Using Geometry.

Key Observation:

Minute Hand Movement: A clock has 360°. Minute hand completes 360° in 60 minutes.

So: 1 minute = 360/60 = 6°

Therefore: minuteAngle = minutes × 6

Hour Hand Movement: Hour hand completes 360° in 12 hours.

So: 1 hour = 360/12 = 30°
But hour hand does NOT stay fixed. It continuously moves as minutes pass.

Example: 3:30
Hour hand is not exactly at 3. It has moved halfway toward 4.

Movement per minute: 30° / 60 = 0.5°

Therefore: hourAngle = hour × 30 + minutes × 0.5

Since 12 and 0 represent same position: hour %= 12

Formula:

hourAngle = (hour % 12) * 30 + minutes * 0.5

minuteAngle = minutes * 6

difference = abs(hourAngle - minuteAngle)

answer = min(difference, 360 - difference)

Approach:

1. First, calculate the angles of hour and minute hands using the above formulas.
2. Then, find the absolute difference between the two angles.
3. Finally, return the smaller angle between the two hands. If the difference is greater than 180°, subtract it from 360° to get the smaller angle.
4. Return the final result.

Dry Run:

Example 1:

hour = 12
minutes = 30

Hour hand: (12 % 12)*30 + 30*0.5 = 0 + 15 = 15°

Minute hand: 30 * 6 = 180°

Difference: |15 - 180| = 165°

Smaller angle: min(165, 360-165) = min(165,195) = 165
Answer: 165

Example 2:

hour = 3
minutes = 15

Hour hand: 3*30 + 15*0.5 = 90 + 7.5 = 97.5°

Minute hand: 15*6 = 90°

Difference: |97.5 - 90| = 7.5°

Smaller angle: min(7.5,352.5) = 7.5
Answer: 7.5

Example 3:

hour = 6
minutes = 0

Hour hand: 180°

Minute hand: 0°

Difference: 180°

Answer: 180°


Time Complexity: O(1) - Constant time complexity as we are performing a fixed number of calculations.

Space Complexity: O(1) - Constant space complexity as we are using a fixed number of variables.


A clock has 360°. The minute hand moves 6° per minute, so its angle is minutes × 6. 
The hour hand moves 30° per hour and also moves continuously at 0.5° per minute, so its angle is (hour % 12) × 30 + minutes × 0.5. 
Then I compute the absolute difference between the two angles and return the smaller angle using min(diff, 360 - diff). 
The solution runs in O(1) time and O(1) space.


 */
// Code:

class AngleBtwHandsOfClock {

    public double angleClock(int hour, int minutes) {

        double hourAngle = (hour % 12) * 30 + minutes * 0.5;
        double minuteAngle = minutes * 6;
        double diff = Math.abs(hourAngle - minuteAngle);
        return Math.min(diff, 360 - diff);
    }
}
