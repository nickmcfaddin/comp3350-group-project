# EasyShopper
Software Engineering Winter 2024 Project created by Lethal Company.

## Main Branch Contributing Members
- Leeroy Dillim
- Hung Tran
- Nick McFaddin
- Yuan Tao
- Jarett Koley

## Branching
In this project we used GitLab flow, a subversion of GitHub flow that works by creating feature branches off of the main branch and using the main as your develop branch.

The only difference is that when you are ready to make a release, you push to a production branch that will contain the latest working release at all times. Thus this will be the branch where we push our iteration 1, 2 and 3 releases.

## Merge Requests
All merge requests should have an appropriate title, consisting of a brief description of what was changed/added. The description may include a more detailed version, including any and all important/relevant technical details if needed.

In your merge request you should check off squash commits and delete source branch as we want to limit the amount of non-active feature branches that are present in the repository. 

In terms of approvals, this will depend on the scope of the request. If you're just adding a few lines of documentation, you don't need any approvals. If you're adding any functional or significant code you must have 1 approval to check your work over.

Additionally, before your merge you should merge develop into your task branch and do a final sanity check to make sure everything works as expected. Then, assuming you have your approvals, you can merge.

## Unit Tests
All methods in the persistence and logic layers as well as DSO's should be tested other than getters and setters. This will ensure that we maintain a high code coverage score and that our project can get as close as possible to follow test driven development.

Even the most basic of unit tests are useful, though everybody needs to make an effort to have unit tests comprehensively test all publically facing functionality.