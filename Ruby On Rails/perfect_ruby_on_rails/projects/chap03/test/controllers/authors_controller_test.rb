require 'test_helper'

class AuthorsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @auther = authers(:one)
  end

  test "should get index" do
    get authers_url
    assert_response :success
  end

  test "should get new" do
    get new_auther_url
    assert_response :success
  end

  test "should create auther" do
    assert_difference('Auther.count') do
      post authers_url, params: { auther: { address: @auther.address, birth: @auther.birth, ctype: @auther.ctype, name: @auther.name, photo: @auther.photo, user_id: @auther.user_id } }
    end

    assert_redirected_to auther_url(Auther.last)
  end

  test "should show auther" do
    get auther_url(@auther)
    assert_response :success
  end

  test "should get edit" do
    get edit_auther_url(@auther)
    assert_response :success
  end

  test "should update auther" do
    patch auther_url(@auther), params: { auther: { address: @auther.address, birth: @auther.birth, ctype: @auther.ctype, name: @auther.name, photo: @auther.photo, user_id: @auther.user_id } }
    assert_redirected_to auther_url(@auther)
  end

  test "should destroy auther" do
    assert_difference('Auther.count', -1) do
      delete auther_url(@auther)
    end

    assert_redirected_to authers_url
  end
end
