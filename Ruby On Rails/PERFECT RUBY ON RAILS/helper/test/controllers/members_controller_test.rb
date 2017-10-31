require 'test_helper'

class MembersControllerTest < ActionDispatch::IntegrationTest
  test "should get login" do
    get members_login_url
    assert_response :success
  end

end
