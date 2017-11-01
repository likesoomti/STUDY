Rails.application.routes.draw do

  root 'home#index'
  
  get 'ctrl/c_params'
  get 'ctrl/c_request'
  get 'ctrl/c_response'
  get 'ctrl/c_headers'

  resources :users
  resources :posts
  get 'members/login'
  get 'home/index'
  get 'home/new'
  post 'home/search'
  post 'home/create'
  get 'home/form_helper'
  get 'home/link_helper'

  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
