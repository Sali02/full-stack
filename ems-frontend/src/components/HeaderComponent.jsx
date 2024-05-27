import React from 'react'
import { NavLink } from 'react-router-dom';

const HeaderComponent = () => {
  return (
    <div>
      <header>
        <nav className='navbar navbar-expand-lg navbar-dark bg-dark'>
          <a className='navbar-brand' href='https://www.youtube.com'>Employee Management System</a>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
               <li class="nav-item">
                <NavLink className={'nav-link'} to={'/employees'}>Employees</NavLink>
              </li>
              <li class="nav-item">
                <NavLink className={'nav-link'} to={'/departments'}>Departments</NavLink>
              </li>
            </ul>
          </div>
        </nav>
      </header>
    </div>
  )
}

export default HeaderComponent;