# SpringBootcamp
## Spring in Action

**Important Notes!!!**
### The issues listed here may be due to my project using newer dependancy versions than the author used.

Chapter 2:
  - Remember to install lombok.jar, just adding the dependancy won't work! (https://projectlombok.org/setup/eclipse)
  - Class Taco() is first used in Listing 2.2, but created in Listing 2.5, and completed in List 2.10
  - Listing 2.2 utilizes filterByType(), but this function is never actually created. Add this to the end of DesignTacoController.java:
      ```
      private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) 
      {
        return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
      }
      ```
  - Class Order is first used in Listing 2.6, but created in Listing 2.9, and completed in List 2.11
  - Book does not implement input validation for DesignTacoController.java. For it to work, code must look like this:
      ```
      @ModelAttribute
      public void addIngredientsToModel(Model model) 
      {
          List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
            new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient("CARN", "Carnitas", Type.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
            new Ingredient("LETC", "Lettuce", Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Type.CHEESE),
            new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Type.SAUCE),
            new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
          );

          Type[] types = Ingredient.Type.values();
          for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                filterByType(ingredients, type));
          }
      }

      @GetMapping
      public String showDesignForm(Model model) 
      {
        model.addAttribute("design", new Taco());
        return "design";
      }

      @PostMapping
      public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors)
      {
          if(errors.hasErrors())
          {
              return "design";
          }

          // Save the taco design...
          // We'll do this in chapter 3
          log.info("Processing design: " + design);

          return "redirect:/orders/current";
      }
      ```
  - Remember to include this import:
      ```
      import org.springframework.web.bind.annotation.ModelAttribute;
      ```
  - Taco.java's input validation for 'ingredients' is wrong. 'Null' elements are considered valid for "@Size(min = 1)". Should use this instead:
      ```
      @NotNull(message = "You must choose at least 1 ingredient")
      ```
  - Book does not modify OrderController.java for input validation. Code must look like this:
      ```
      @PostMapping
      public String processOrder(@Valid @ModelAttribute("order") Order order, Errors errors)
      {
          if(errors.hasErrors())
          {
              return "orderForm";
          }

          log.info("Order submitted: " + order);
          return "redirect:/";
      }
      ```