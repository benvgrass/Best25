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
 overall. Another choice was how to partition the roster. 
 
 The roster will
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
   being programmatically generated.
   
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

### Compiling the Data
Data was compiled in a fairly simple manner. First, I went to the
 [Fangraphs](http://www.fangraphs.com/) leaderboard section, set multiple 
 seasons to be from 1992 (25 years from now) to 2016. I then split the 
 seasons and created a custom leaderboard that just included WAR (because 
 season and name are automatically included). I then selected the team to be 
 Blue Jays.Then, I went through each position and exported the data. The 
 results of this can be seen in [data/jays_raw](/data/jays_raw). I then 
 compiled that into a [final CSV](/data/jays.csv) which included the positions of 
 the players 
 so that it could be easily read into the program. There are a few things of 
 note here. 
 1) Players can be listed with same season statistically for multiple 
 positions for a particular year. An example would be Edwin Encarnación in 
 2015, where he was listed as both a first baseman and DH. This isn't really 
 a problem because players can't be repeated so really it just adds a 
 dimension of positional versatility. This is also intersting in the case of 
 backup outfielders, which can be anyone from the OF list, which is composed 
 of all outfielders in all years. This allows positional versatility for the 
 backups, but it could create a situation where one of the two OF positions 
 (which should be bench players) have a higher WAR value than that of a 
 starter in the outfield. This is actually likely in the case of left field, 
 where left fielders probably won't end up being the backups, but if there is
  a situation where manually I can switch a supposed backup with a higher WAR
   than a starter where the backup actually players the starting position I 
   will manually do so at the end. 
  2) We switched the order of the characters for the position "1B", "2B", and
   "3B" because we thought it would make it easier to parse. We then changed 
   the parser and but there is no compelling reason to change it back. 
  3) There are Fangraphs player IDs everywhere and may be used to distinguish
   players rather than using name, yet may not have a use. 
 
 After all of that the data was ready to use in our program!


## Inspiration

[This Bluebird Banter article...](http://www.bluebirdbanter.com/2017/2/2/14483260/relief-market-reese-mcguire-payroll-25-man-roster)

[Which led to his article...](http://toronto.locals.baseballprospectus.com/2017/02/01/blue-jays-25-year-25-man-roster)

Which led to the following tweet:

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">Pick one franchise. One guy from each of the last 25 years. No repeated players. Must form a coherent 25-man. Don&#39;t cheat on positions. Go.</p>&mdash; Russell A. Carleton (@pizzacutter4) <a href="https://twitter.com/pizzacutter4/status/825015479882309632">January 27, 2017</a></blockquote>
<script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
