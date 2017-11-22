Rails.application.routes.draw do

  resources :posts
  resources :qnas
  root 'home#index'

  get 'home/index'
  get 'home/red'
  post 'home/red'
  get 'home/blue'
  get 'home/green'
  get 'home/yellow'

  get 'home/:url' => 'home#color'

  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
