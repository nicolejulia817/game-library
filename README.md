# game-library

Note: this is a WIP. Check out the src folder to see my progress so far.

This project works with data regarding video game ratings. I surveyed college students using Google Forms, asking them to rate games on a scale of 1 to 5 stars. I downloaded their responses as a CSV file. My program will be able to read the CSV file and organize the data. It will also be able to calculate the bayesian averages the games as well as the top K games (for example, top 3 highest rated games). My goal is to ultimately display the data on a page in my website and have users be able to add more ratings from the page, updating the results in real-time. My tests are located in src/tests.

TO-DO:
  - Implement Cuckoo hashing in File Reader to have an efficient add and lookup of games.
  - Based on how I will implement the final program into my webpage, implement Main.
