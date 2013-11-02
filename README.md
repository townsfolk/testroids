Problem
=========

The data is passed forward as the user navigates through the views from:
Leagues -> Teams in a League -> Players on a Team

It doesn't stick around when the user presses the back or home buttons.

I have ```setRetainInstance(true)``` but the ```mLeagueName``` field in ```LeagueFragment``` is null
after the back button is pressed.

I've put logging in all the methods I think are important (I'm probably wrong).

Here is the logs from the transition from ```LeagueFragment``` to ```TeamFragment``` and back to ```LeagueFragment```:
```
11-01 23:18:01.742  14136-14136/? D/testroids.LeagueFragment﹕ onCreate
11-01 23:18:01.742  14136-14136/? D/testroids.LeagueFragment﹕ league name: Baseball
11-01 23:18:01.742  14136-14136/? D/testroids.LeagueFragment﹕ onCreateLoader
11-01 23:18:01.742  14136-14136/? D/testroids.LeagueFragment﹕ onCreateView
11-01 23:18:01.752  14136-14136/? D/testroids.LeagueFragment﹕ LoadFinished - team count: 1
11-01 23:18:06.758  14136-14136/? D/testroids.TeamFragment﹕ onCreate
11-01 23:18:06.758  14136-14136/? D/testroids.TeamFragment﹕ onCreateLoader
11-01 23:18:06.758  14136-14136/? D/testroids.TeamFragment﹕ onCreateView
11-01 23:18:06.768  14136-14136/? D/testroids.TeamFragment﹕ LoadFinished - player count: 1
11-01 23:18:12.694  14136-14136/? D/testroids.LeagueFragment﹕ onCreate
11-01 23:18:12.694  14136-14136/? D/testroids.LeagueFragment﹕ league name: null
11-01 23:18:12.694  14136-14136/? D/testroids.LeagueFragment﹕ onCreateLoader
11-01 23:18:12.694  14136-14136/? D/testroids.LeagueFragment﹕ onCreateView
11-01 23:18:12.704  14136-14136/? D/testroids.LeagueFragment﹕ LoadFinished - team count: 1
```

If my understanding of ```setRetainedInstance(true)``` is correct, then this log output should contain the league name instead of null:
```
11-01 23:18:12.694  14136-14136/? D/testroids.LeagueFragment﹕ league name: null
```
