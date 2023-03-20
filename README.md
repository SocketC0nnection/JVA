[discord]: https://discord.gg/r8sxjdgHxr
[license]: https://github.com/SocketC0nnection/JVA/tree/master/LICENSE
[discord-img]: https://discordapp.com/api/guilds/1087072997736714310/widget.png
[discord-banner]: https://discordapp.com/api/guilds/1087072997736714310/widget.png?style=banner2
[license-img]: https://img.shields.io/badge/License-Apache%202.0-white.svg

<img align="right" src="https://i.ibb.co/HBw4Bxw/Untitled-Design.png" height="200" width="200">

[ ![license-img][] ][license]
[ ![discord-img][] ][discord]


# JVA (Java Valorant API)

JVA is a java-based wrapper for the following Valorant Rest API:

https://github.com/Henrik-3/unofficial-valorant-api v2.6.2

This API is free and freely accessible for everyone. An API key is optional but not mandatory. This project is still being worked on regularly.

This is the first version. There could be some bugs, unexpected exceptions or similar. Please report bugs on our [discord].

### API key

You can request an API key on [Henrik's discord server](https://discord.com/invite/X3GaVkX2YN) <br> It is NOT required to use an API key though!

## Summary

1. [Introduction](#introduction)
2. [Download](#download)
3. [Documentation](#documentation)
4. [Support](#support)

## Introduction

Some requests may take longer. The HTTP requests are not threaded. Use threads by yourself!

### Set up the project

```java
public static void main(String[] args) throws IOException {
    // Initialize the main instance (API key is NOT required)
    ValorantAPI valorantAPI = new ValorantAPI("API_KEY");
}
```
If you dont want to use an API key, leave the constructor of `ValorantAPI` empty

### Get player informations

```java
public static void main(String[] args) throws IOException {
    // Initialize the main instance (API key is NOT required)
    ValorantAPI valorantAPI = new ValorantAPI("API_KEY");
    // Pass the main instance to the player instance and fill the player with an username and tag or riot id
    ValorantPlayer valorantPlayer = new ValorantPlayer(valorantAPI).fetchData("USERNAME", "TAG");

    // Read out the rank using the Rank enum for example
    Rank rank = valorantPlayer.getRank();
    // Print out the rank using the getRankName() method
    System.out.println(rank.getRankName());

    // Read out the level
    int level = valorantPlayer.getLevel();
    // Read out the last amount of RR he got or lost
    int mmrChange = valorantPlayer.getMmrChange();
        
    // Get the players banner using the PlayerCard model
    PlayerCard playerCard = valorantPlayer.getPlayerCard();
    // Print out the banners url in different sizes
    System.out.println(playerCard.getSmall());
    System.out.println(playerCard.getLarge());
}
```

Classes like `PlayerCard` and `RankImage` are model classes that only return values

### Get a players match history

```java
public static void main(String[] args) throws IOException {
    // Initialize the main instance (API key is NOT required)
    ValorantAPI valorantAPI = new ValorantAPI("API_KEY");
    // Pass the main instance to the player instance and fill the player with an username and tag or riot id
    ValorantPlayer valorantPlayer = new ValorantPlayer(valorantAPI).fetchData("USERNAME", "TAG");

    // Saves the last 5 matches in an array of the Match object
    Match[] matches = valorantPlayer.getMatchHistory();
    // Saves the last one in a variable
    Match match = matches[0];

    // Saves the map in a variable using the Map enum
    Map map = match.getMap();
    // Print out the map using the getMapName() method
    System.out.println(map.getMapName());

    // Get match id and region of a match using the Region enum
    String matchId = match.getMatchId();
    Region region = match.getRegion();

    // Get other players and their values from a match using the MatchPlayer instances
    List<MatchPlayer> matchPlayers = match.getPlayers();
    MatchPlayer matchPlayer = matchPlayers.get(0);

    // Print out stats of a match player
    System.out.println(matchPlayer.getStats().getKills());
    // Print out informations about a match players behavior and economy
    System.out.println(matchPlayer.getBehavior().getAfkRounds());
    System.out.println(matchPlayer.getEconomy().getOverallSpent());
}
```

The `MatchPlayer` class extends from the `ValorantPlayer` class, so you have all the methods from the `ValorantPlayer` class

### Get a match using the match id

```java
public static void main(String[] args) throws IOException {
    // Initialize the main instance (API key is NOT required)
    ValorantAPI valorantAPI = new ValorantAPI("API_KEY");
    // Pass the main instance to the match instance and fill the match with a match id
    Match match = new Match(valorantAPI).fetchData("MATCH ID");
}
```

### Get the leaderboard for a region

```java
public static void main(String[] args) throws IOException {
    // Initialize the main instance (API key is NOT required)
    ValorantAPI valorantAPI = new ValorantAPI("API_KEY");
    // Get the first 1000 leaderboard entries in a list using the Leaderboard class
    List<LeaderboardPlayer> leaderboardPlayers = valorantAPI.getLeaderboard(Region.EUROPE);
    LeaderboardPlayer leaderboardPlayer = leaderboardPlayers.get(0);
        
    // Print out the current placement and rank rating of the first player
    System.out.println(leaderboardPlayer.getLeaderboardRank());
    System.out.println(leaderboardPlayer.getRankRating());
}
```

The `LeaderboardPlayer` class extends from the `Player` class, so you automatically have informations like player id, username, tag and rank

### Get infos about versions of a region

```java
public static void main(String[] args) throws IOException {
    // Initialize the main instance (API key is NOT required)
    ValorantAPI valorantAPI = new ValorantAPI("API_KEY");
    // Saves data about versions of a region in a Version model
    Version version = valorantAPI.getVersion(Region.EUROPE);
        
    // Print out client and server version
    System.out.println(version.getVersion());
    System.out.println(version.getClientVersion());
}
```

### Get the last articles from the official VALORANT page

```java
public static void main(String[] args) throws IOException {
    // Initialize the main instance (API key is NOT required)
    ValorantAPI valorantAPI = new ValorantAPI("API_KEY");
    // Get the last articles from the valorant page in a specific language using the Language enu
    List<WebsiteArticle> websiteArticles = valorantAPI.getWebsiteArticles(Language.ENGLISH);
    WebsiteArticle websiteArticle = websiteArticles.get(0);
        
    // Print out title and url of the last article
    System.out.println(websiteArticle.getTitle());
    System.out.println(websiteArticle.getUrl());
}
```

### Get current maintenances and incidents for a specific region

```java
public static void main(String[] args) throws IOException {
    // Initialize the main instance (API key is NOT required)
    ValorantAPI valorantAPI = new ValorantAPI("API_KEY");
    // Get the last maintenances and incidents using the ServerStatus model
    ServerStatus serverStatus = valorantAPI.getServerStatus(Region.EUROPE);
        
    // Print out a current incident in a specific language using the Language enum
    System.out.println(serverStatus.getIncidents()[0].getTitles().get(Language.ENGLISH));
}
```

## Download

Latest Release: [GitHub Releases](https://github.com/SocketC0nnection/JVA/releases/latest)

**Maven**
```xml
<dependency>
    <groupId>net.socketconnection.jva</groupId>
    <artifactId>jva</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Documentation

The detailed documentations are still in progress.

## Support

For support visit our [discord] server

[ ![discord-banner][] ][discord]