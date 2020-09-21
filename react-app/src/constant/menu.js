import {
    PieChart,
    Clipboard,
    User,
    CheckSquare,
    Paperclip,
    Settings,
    BookOpen
    
} from 'react-feather';

export const MENUITEMS = [
    // {
    //     title: 'Dashboard', icon: Home, type: 'sub', badgeType: 'primary', active: false, children: [
    //         { path: '/dashboard/default', title: 'Default', type: 'link' },
    //         { path: '/dashboard/ecommerce', title: 'E-Commerce', type: 'link' },
    //         { path: '/dashboard/university', title: 'University', type: 'link' },
    //         { path: '/dashboard/crypto', title: 'Crypto', type: 'link' },
    //         { path: '/dashboard/project', title: 'Project', type: 'link' }
    //     ]
    // },
    {
        title: 'Dashboard', icon: PieChart, type: 'link', path: '/support-ticket/supportTicket', active: true
    },
    {
        title: 'Customers', icon: User, type: 'link', path: '/support-ticket/supportTicket', active: false
    },
    {
        title: 'Schedules', path: '/sample/samplepage', icon: Clipboard, type: 'link', active: false
    },
    {
        title: 'Jobs', path: '/sample/samplepage', icon: CheckSquare, type: 'link', active: false
    },
    {
        title: 'Reports', path: '/sample/samplepage', icon: Paperclip, type: 'link', active: false
    },
    {
        title: 'Books', path: '/sample/samplepage', icon: BookOpen, type: 'link', active: false
    },

    {
        title: 'Settings', path: '/sample/samplepage', icon: Settings, type: 'link', active: false
    },
]

