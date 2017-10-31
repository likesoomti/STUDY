class FoodController < ApplicationController
  def pizza
  end

  def pizza_ok
    @pizza_number = params["pizza_order"]

    puts "출력해라"
    puts @pizza_number
  end

  def chicken
  end
end
