Rails.application.routes.draw do

  root 'home#index'
  
  get 'home/index' # => 'home#index'

  get 'home' => 'home#index'
  get 'food/pizza'
  get 'food/pizza_ok'

  get 'food/chicken'

  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end


