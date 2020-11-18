package com.cuidedacidade.features.pendingrequests

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.cuidedacidade.R
import com.cuidedacidade.core.CCidadeApplication
import com.cuidedacidade.core.auth.AuthManager
import com.cuidedacidade.features.baserequests.BaseRequestsFragment
import com.cuidedacidade.utils.SnackbarUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_requests.*
import javax.inject.Inject

class PendingRequestsFragment : BaseRequestsFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<PendingRequestsViewModel> { viewModelFactory }

    @Inject
    lateinit var appAuthManager: AuthManager

    override fun setupDI() {
        (requireActivity().application as CCidadeApplication).appComponent
            .pendingRequestsComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_title_pending_requests.visibility = View.VISIBLE
        swp_requests.setOnRefreshListener(viewModel::refreshPendingRequests)
        viewModel.getPendingRequests().observe(viewLifecycleOwner, observerRequests)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.pending_requests, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSignOut) {
            signOut()
            return true
        }
        val navController = findNavController()
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        appAuthManager.signOut(requireActivity(), {}, {
            SnackbarUtils.show(
                requireActivity().mainCoordinatorLayout,
                R.string.something_unexpected_happened_try_again_later
            )
        })
    }
}