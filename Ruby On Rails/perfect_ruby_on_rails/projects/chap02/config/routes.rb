Rails.application.routes.draw do
  get 'hi/new'

  get 'hi/show'

  get 'hi/destroy'

  get 'hello/show'

  get 'hello/new'

  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  match ':controller(/:action(/:id))',via: [ :get,:post,:patch]
end
