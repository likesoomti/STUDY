Rails.application.routes.draw do
  get 'members/login'
  get 'home/index'
  get 'home/new'
  post 'home/search'
  post 'home/create'
  get 'home/form_helper'
  get 'home/link_helper'

  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
