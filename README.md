# My Project- A Recipe converter

## Usage, Application, and Purpose

An *Comprehensive* answer:
- The application will take in a recipe and some amounts of ingredients at hand
. It will then pick out the limiting ingredient and produce a version of the 
original recipe, with the portion of all the ingredients adjusted according
to the limiting ingredient. So the person using the recipe can maximize their output
and save time in finding out the quantity of each ingredient needed. It could also
be used to scale up or down a recipe, for people who would like to see the numbers 
calculated for them and clearly listed.
- This maybe useful for anyone who doesn't have the exact amount of ingredients 
stated in the recipe, and wants to maximize the amount of food they make.
This could be a useful application, for people who bake or cook, or extension 
for recipe websites.
- I am a baker my self and I know how frustrating it can be to not have enough ingredients 
required by the recipe at hand. Sometimes if the proportion of the ingredients are complicated
it is hard to adjust the amount of ingredients by hand. If I were to have an application 
like this one, I could save so much time doing math calculations.

## User Story
- I want to be able to add original and adjusted recipes to my recipe collection (done)
- I want to be able to view my recipe collections (done)
- I want to be able to scale my recipes (done)
- I want to be able to access the original recipe through the scale recipe through a link
- I want to be able to specify the portion, preparation time, and list of ingredients for each recipe (done)
- I want to be able to filter my recipes according to preparation time and ingredients
- I want to be able to save my recipe (done)
- I want to be able to load my saved recipe when restarting the program (done)

TODO:
- cannot add recipe, cannot see recipe in view recipe
- view recipe row too narrow
- need save box
- need load box

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the 
add recipe button, inputting required fields and click submit. By clicking the home button you can check if 
the recipe is added by clicking the view recipe button.
- After having at least one recipe in the recipe book, you can scale recipe by clicking scale recipe button on the home 
page. There are two panels on the screen after button is pressed. The first panel allows you to scale the recipe by 
taking a number and multiplying the ingredient amount by that number, then the recipe will be added to the view recipe 
panel. The second way is to take in an ingredient name and an amount of that ingredient you have on hand, and scale the
recipe like in chemistry with limited ingredient. The result too can be seen in the view recipe panel after hitting 
submit. You will have to click the home button to see the view recipe button.
- You can locate my visual component by looking at the image icon on the buttons
- You can save the state of my application by pressing save recipe button on home page
- You can reload the state of my application by pressing load button on home page. Recipes will be loaded into the view
recipe panel, which can be accessed by clicking the view recipe button on the home page.

# Phase 4 task 2
- Sun Apr 07 11:10:53 PDT 2024
  Added recipe: salad

# Phase 4 task 3
- Looking at the diagram, I realize the amount of coupling in my program is very high. I should probably do something to
reduce the amount of coupling. Since the high coupling will lead to a series of problems when I change any one aspect
of the model classes. However, since a lot of the referencing to the recipe class was to use the constructor, I can 
seem to think of a way to reduce the coupling. There is also a lot of fields being declared in the ScaleRecipePanel
class. I could make some of the fields local instead of universal since we do want to make variable as local as possible
to avoid complications in the future. 