# Best 25 Year Team
## Description
Using CS to determine what combo of players (given a franchise) would be the best in terms of fWAR over the last 25 years with one player taken from each year, complete team filling every position with no duplicates. Using the Blue Jays because Go Jays!

## Methodology
### Design Choices
The prompt is quite open ended, but what I was interested in particularly is 
how to maximize the value of a team based on the given criteria. In order to 
do this, there were a few choices to make in terms of how to design the team.
 WAR was an easy choice for maximization criteria because it attempts to 
 represent a value for a player, so using that you'd assemble the team that 
 could potentially be the most valuable and thus in my mind most productive 
 overall. Another choice was how to partition the roster. <br>The roster will
  be partitioned as follows:
   - 5 Starting Pitchers
   - 7 Relief Pitchers
   - 4 Bench Players (Catcher, 1 Infielder (a shortstop for versatility), 2 
   Outfielders)
   - 8 Starting Position Players
   - 1 DH
 
 This is only valid for an AL team so maybe for adaptability this will be 
 changed going forward. The only real choice here was between 7/8 man bullpen
  and 3/4 man bench, but I figured that because WAR for relievers tends to be
   pretty low and the system may waste a good position player year to 
   accommodate a reliever, but in reality I just felt like another position 
   player would be more interesting than an extra reliever. The Baseball 
   Prospectus article ([see below](#Inspiration)) had a 5 man bench so I figure
   may as well go with a more normal 4 and have some basis for comparison 
   between the author's list and the one generated from this, though she used
   bWAR for her WAR versus this system using fWAR so theoretically there 
   could be some differences in the concept of value on top of her list not 
   being programmatically generated.<br>
   Another thing of note is that I chose to not adjust for the fact that 
   relievers may be undervalued in terms contributing to wins in WAR, so the 
   team that is produced will likely not put an emphasis on taking strong 
   reliever seasons where it can find better position player seasons because 
   it just wants to maximize WAR, not create a balanced team. The result of 
   this may be that the relievers are relatively lacking, though I'm somewhat
    curious as to what the bullpen will look like. Maybe the WAR lost by 
    maximizing reliever seasons will be harder to make up by poorer relievers
     rather than the difference between one player-season to another. That 
     being said, relievers are not treated specially because they have a 
     fundamentally different role than other players.  
      

## Inspiration

[This Bluebird Banter article...](http://www.bluebirdbanter.com/2017/2/2/14483260/relief-market-reese-mcguire-payroll-25-man-roster)

[Which led to his article...](http://toronto.locals.baseballprospectus.com/2017/02/01/blue-jays-25-year-25-man-roster)

Which led to the following tweet:

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">Pick one franchise. One guy from each of the last 25 years. No repeated players. Must form a coherent 25-man. Don&#39;t cheat on positions. Go.</p>&mdash; Russell A. Carleton (@pizzacutter4) <a href="https://twitter.com/pizzacutter4/status/825015479882309632">January 27, 2017</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
