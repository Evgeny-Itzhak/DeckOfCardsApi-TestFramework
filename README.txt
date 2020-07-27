DeckOfCardsApi-TestFramework

How to run via Gradle:
1.Open terminal in project directory
2.preform gradle clean build
3.preform gradle allureReport
4.preform gradle allureServe

How to run via Maven:
1.Open terminal in project directory
2.preform mvn clean test
3.preform mvn allure:serve

Summary of the project
My approach during the writing framework was very simple. At first, I was intended to perform all tasks in the way I know how to do it, and then when it be working I was going to use technologies that were mentioned in the task, that I do not have previous experience working with.
So I did the first part and because of the lack of time, I did not use some tech from the task (Cucumber). I know how to implement it, but I need more time to get it done well for all other tests. That's why I've decided to leave it as is.
And as for the Gradle, I did not have any experience with it at all, that why I decided to use Maven (also because it was in all my previous projects). And then after it started work with Maven I have investigated and added Gradle. That's why I have in the project both Maven and Gradle.

Summary about issues
During testing this API I've found several issues. For instance one of it in "Listing Cards in Piles" endpoint.
If we perform this request with incorrect "deck_id" and "pile_name" values (https://deckofcardsapi.com/api/deck/incorrect_deckid/pile/incorrect_pile/list/) we get Server Error (500), but I think it is not good code error for this issue.
As far as I know the best way to handle it is return 400 Bad Request code, because it is not a server issue, but issue with parameters that entered by user.
And the same issue with others endpoints.

Summary about task
It seems to me that 3d task has an incorrect description.
Here it is:
3. Draw 5 specific cards from a bottom of the deck and check that card amount changed and drawn cards are no longer in the deck.
Via this API we can't draw cards from the bottom of the deck. We can draw cards only from the bottom of the pile.
And also we can't draw specific cards from the bottom of the pile it always will be random cards.