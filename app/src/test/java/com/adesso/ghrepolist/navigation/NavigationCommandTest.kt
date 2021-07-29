package com.adesso.ghrepolist.navigation

import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import com.adesso.ghrepolist.data.remote.model.RepoOwnerModel
import com.adesso.ghrepolist.internal.popup.PopupListener
import com.adesso.ghrepolist.internal.popup.PopupModel
import com.adesso.ghrepolist.scene.repolist.RepoListFragmentDirections
import io.mockk.mockk
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert
import org.junit.Test

class NavigationCommandTest {

    @Test
    fun `when NavigationCommand Popup called should return PopupModel with specific values`() {
        val mockPopupModel = PopupModel(
            "title",
            "message",
            false,
            "positive",
            "negative"
        )

        val myNavPopup = NavigationCommand.Popup(mockPopupModel, PopupListener({}, {}))

        Assert.assertEquals("message", myNavPopup.model.message)
    }

    @Test
    fun `when NavigationCommand ToDirection called should contain direction`() {
        val gitHubRepoModelItem = GitHubRepoModelItem(
            "repo1",
            RepoOwnerModel("user1", "userUrl1"),
            10,
            20,
            false
        )

        val myNavToDirection = NavigationCommand.ToDirection(
            RepoListFragmentDirections.actionRepoListFragmentToRepoDetailFragment(
                gitHubRepoModelItem
            )
        )

        assert(myNavToDirection.directions.actionId != 0)
    }

    @Test
    fun `when NavigationCommand Back called should contain direction`() {
        val back: NavigationCommand = NavigationCommand.Back

        Assert.assertTrue(back is NavigationCommand.Back)
        Assert.assertThat(back, instanceOf(NavigationCommand.Back::class.java))
    }

}